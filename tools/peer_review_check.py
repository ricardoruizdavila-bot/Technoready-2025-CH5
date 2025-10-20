import os
import re
from datetime import datetime

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), '..'))
LOG_DIR = os.path.join(ROOT, 'tools', 'logs')
os.makedirs(LOG_DIR, exist_ok=True)
LOG_FILE = os.path.join(LOG_DIR, 'peer_review.log')

LEVEL = os.environ.get('PEER_REVIEW_LEVEL', 'medium').lower()  # low|medium|high

def log(msg):
    with open(LOG_FILE, 'a', encoding='utf-8') as f:
        f.write(f"{datetime.utcnow().isoformat()} {msg}\n")

def scan_files():
    issues = []
    patterns = {
        'TODO': re.compile(r'\bTODO\b'),
        'FIXME': re.compile(r'\bFIXME\b')
    }
    for subdir, _, files in os.walk(ROOT):
        # skip logs and virtual envs
        if 'node_modules' in subdir or '.git' in subdir:
            continue
        for fname in files:
            if fname.endswith(('.java', '.kt', '.py', '.yml', '.yaml', '.properties', '.md', '.ts', '.js')):
                path = os.path.join(subdir, fname)
                try:
                    with open(path, 'r', encoding='utf-8', errors='ignore') as fh:
                        content = fh.read()
                except Exception:
                    continue
                for name, pat in patterns.items():
                    for m in pat.finditer(content):
                        if LEVEL == 'low' and name == 'TODO':
                            continue
                        issues.append((path, name, m.start()))
    return issues

def simple_config_checks():
    issues = []
    # Check prod yml for missing required env placeholders
    prod_path = os.path.join(ROOT, 'src', 'main', 'resources', 'application-prod.yml')
    if os.path.exists(prod_path):
        content = open(prod_path, 'r', encoding='utf-8').read()
        if '${PROD_DB_URL' not in content:
            issues.append((prod_path, 'CONFIG', 'PROD DB placeholder missing'))
    return issues

def main():
    log("=== Peer review run started ===")
    issues = scan_files()
    issues += simple_config_checks()
    if not issues:
        log("No issues found by partial review.")
        print("No issues found.")
        return 0
    for path, kind, pos in issues:
        entry = f"{kind} | {path} | pos:{pos}"
        log(entry)
        print(entry)
    log(f"Found {len(issues)} issues.")
    return 1

if __name__ == '__main__':
    exit(main())


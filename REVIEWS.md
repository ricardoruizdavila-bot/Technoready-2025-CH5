# Partial Peer Reviews — Sprint 2

## 2025-10-19 – Config & Profiles
- Checked: application-dev/test/prod.yml correct separation.
- Verified: profile switching via SPRING_PROFILES_ACTIVE.
- Verified: no secrets committed; prod reads DB_* from env.
- Issues found:
    - [ ] (example) Ensure H2 console disabled in prod (OK).
      Reviewer: <name>

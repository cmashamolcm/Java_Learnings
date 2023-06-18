### Git basics
1. Hooks:
    - Some scripts (bash/ sh/ python/ go) that is to be triggered when a git event happens.
    - In folder `.git/<hook name>`
    - Eg: `./git/pre-commit`, `./git/post-receive`
    - 2 types:
      - Local hooks/ Client-Side hooks (client side hooks). Happens in developer machine
      - Eg: pre-commit
      - Server Side hooks. Works in git remote repo on any git action such as push, after receive push etc.
      - Eg: post-merge, post-receive (pushed code to repo)
      - Helps to improve workflow
      - https://medium.com/@f3igao/get-started-with-git-hooks-5a489725c639
2. Workflow:
   - Some rules and practices followed by a team
   - vs Hook: Hook is an action within the different stages of workflow to enforce run tests, code formatting on commit etc.
3. Git pull vs Fetch:
    Git pull = git fetch + git merge
    Git fetch gets the changes to local repo but not to actual files.
    Git merge updates the actual file system.
    So, git pull gets changes to local repo and updates the working directory.
    When a pull fails, fetch is happened and merge will be pending.
    `Use fetch, if we want to update local repo and has local changes to commit. If no local changes, safe to do a pull`
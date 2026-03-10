package week6;

/**
 *  Daily work, Agile, Scrum, Day to day responsibility
 *
 *  Jira -> software for agile
 *  Backlog: Todo List with priority
 *  Sprint : 1 ~ 3 weeks,  most sprints are 2 weeks
 *  Sprint planning meeting : plan stories , tickets ... for sprint
 *  Daily stand up : what you have finished / done yesterday / what's your plan today
 *  Grooming : assign points
 *      1 point = 1 / 2 / 4 hours
 *      fibonacci number = difficult level (1, 2, 3, 5, 8)
 *  Retrospective meeting / sprint review meeting :
 *  Demo review meeting : show demo to product owner
 *
 *
 *  Scrum Team
 *  3 ~ 8 people
 *
 *  Manager (Scrum Master, BA)
 *  Leader (one of the SDE)
 *  Backend + Frontend + QA + devops + little bit Data engineer = Full stack developer + Gen Ai / ETL exp
 *  shared DBA
 *
 *  Git repo
 *  main branch (prod env)          --------1.0
 *  (hotfix branch)                          \
 *  release branch (uat env)        --------1.0--------1.1---------1.2
 *                                            \         \           \
 *  development branch (dev env)    -----------------------------------------------------------------
 *                                                           \          / PR(pull request code review) + merge
 *  feature branch (local env)                                ----------
 *
 *
 *  Terraform Repo
 *      1. one git repo for everything
 *          /modules
 *              /s3
 *                  main.tf
 *              /..
 *                  main.tf
 *         /env
 *              /dev
 *                  ..
 *              /uat
 *              /prod
 *
 *      2. two git repos
 *          modules git repo
 *          env setting git repo (use module git link in env setting)
 *
 *  CI/CD pipeline
 *  diff pipelines for diff branches
 *
 *  1. PR + merge code
 *  2. trigger github webhook call pipeline
 *  3. pipeline(server + UI)
 *          a. build
 *          b. test
 *               unit test : test one method / one layer / one unit
 *               integration test: rest assure
 *          c. report : SonarQube (server + UI)
 *               code coverage : jacoco
 *               code smell
 *               code sanity check ..
 *          d. package
 *               package app into docker image (using docker file)
 *               push your docker image -> ECR
 *          e. deploy
 *               deploy to ECS based on Task Definition
 *          f. post deploy
 *               automation test : selenium ..
 *
 *
 1. US internship / working exp
 1.please write down design document for your most recent project.
 design document includes
 1. features / functionalities  / system purpos + overview / clients, why
 2. database schema (tables)
 3. high level design (microservice architecture) and provide module details
 4. rest api design (design 2 - 4 rest apis)
 5.Data flow, prepare 2 - 3 data flow diagram (example: when user client some buttons to upload some files, what happens next, how does request go through your services)
 6. message queue story
 7. biggest challenge(technical challenge)
 8. aws
 9. how did you monitor your application(log monitor / jvm monitor / api monitor)
 10. a story about production support
 11. a story about performance tuning
 12. a story about most recent api you developed
 2.Prepare stories based on your resume: example,  where did you use multi-threading in your last project? Where did you use builder design patterns in your last project?
 3. Come up team size
 4. Design a ci/cd pipeline flow to (AWS / local) depends on your resume project(if you want to keep AWS)
 5. daily user / TPS / QPS
 6. frontend story
 7. monitor tools (jvm , api performance, alert, log, and other monitor tools)
 8. what metrics + alerts do you have
 9. what do you save in log (what fields, diff correlation id, trace id, span id.. other fields)

 Deadline next Monday morning
 upload your design to google doc -> share google doc link with me
 ****Business flow*******
 */
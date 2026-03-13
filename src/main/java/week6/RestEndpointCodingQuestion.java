package week6;


/**
 * during the interview
 * 1. write down the question in comment
 * 2. clarify question
 * 3. write down table design and rest api design
 * 4. impl rest endpoints
 *      create packages (lowercase)
 *      create controller/service
 *      handle exception
 *      keyboard short cut -> create getter setter / create implementations
 *
 */


/*
    write crud for student / university

    q1: crud -> get, create, update , delete
    q2: only student table or also need to design class/teacher table ?
    q3: controller , service ? or need to impl repository ? hard code repository ?
        exception ?
        design table first ?
    ...

    table: student(id(pk), name, age)

    get all student
        endpoint: /students
        query parameter: ?age=x&..
        http method: get
        status code: 200, 400, 500, 401, 403
        request body: none
        response body:
            {
                "timestamp": ..,
                "code": ..,
                "content": [{stu info}, {..}]
            }
    get student by id
        endpoint: /students/{id}
        http method: get
        status code: 200, 404, 500, 401, 403
        request body: none
        response body:
            {
                "timestamp": ..,
                "code": ..,
                "content": {stu info}
            }
    create student
        endpoint: /students
        http method: post
        status code: 200/201, 400, 500, 401, 403
        request body:
            {
                "name": "..",
                "age" : xx
            }
        response body:
            {
                "timestamp": ..,
                "code": ..,
                "content": {"id": xxxx, ""}
            }

 */
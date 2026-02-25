package week4;

/**
 *  primary key : unique , not null , one of the primary key
 *  candidate key: minimum unit
 *  super key: candidate key + extra column
 *
 *
 *  1st : cannot put multiple values in same cell
 *              name                        first_name,  last_name
 *      firstname lastname          ->
 *  2nd : non-prime attributes fully depend on prime attribute
 *      (book_id, author_id), book_name, author_name
 *          1       a1          java        Tom
 *          1       a2          java        Alex
 *          2       a1          c#          Tom
 *  3rd : no transitive relationship among non-prime attributes
 *       id, name, address_id, address
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  car_info: car_type, car_color, year, id
 *                  bmw
 *                  ...
 *  bed_info: bed_size, year, id
 *                  king
 *                  queen ..
 *  user_info: age, gender, id,
 *              ..
 *  ... may add more info in future
 *
 * 1. each table per info
 * 2. shared table + diff sub info table
 * 3. use generic name: col1, col2, col3, col4
 * 4. json
 * 5. entity attribute value
 *
 *      id, info_type, col_name, col_type, col_value , info_id
 *      1 ,   A1   , car_type,  varchar,  bmw       ,  ...
 *      2,    A1   , car_color, varchar,  white     ,  ...
 *      3,    A2   ,  age      , number ,   25
 *
 *      info_details
 *      info_type,  decription ...
 *       A1     , for car info ...
 *       A2     , for ....
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 * Tomorrow plan
 *      1. how oracle works
 *      2. security
 *          Spring security
 *          JWT
 *          HTTPS = HTTP + TLS / SSL
 *          Oauth2.0
 *          OpenID
 *          CORS
 *          CSRF
 *          ..
 *
 *
 */
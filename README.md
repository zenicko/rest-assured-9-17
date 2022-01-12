
![](readme-images/logo-transparent.png) ![](readme-images/name-transparent.png) 

# Hi! I'm readme file and will tell you about this project.
___
Do you love test UI? No?! Why? Because it's **slowly** and **unstability**.

Okey-dokey, let's start testing API.

The project shows how you can test API by using the library `Rest-assured`.


## Acknowledgements
___
[Vasenkov Stanislav](https://github.com/svasenkov)
[Maintenance and technical support website https://reqres.in/](https://reqres.in/)
[Maintenance and technical support website http://demowebshop.tricentis.com](http://demowebshop.tricentis.com)

## About the hometask
___
### Part 1. Lesson 17. REST API. Пишем автотесты с Rest-assured
1. Разработайте 5 автотестов на запросы из https://reqres.in/
2. Дайте код на ревью коллегам из своей группы. После ревью они должны поставить вашему репозиторию звездочку. 
 
**Note:** сдаче принимается дз с минимум 2мя звездами:
![](readme-images/hometaskresult.png)

### Part 2. Lesson 18. REST API. Продолжаем изучать
1. Исследовать сайт магазина http://demowebshop.tricentis.com/
2. Aвтоматизировать проверку ui-взаимодействия по api (разработать любой автотест)

Например: сделать тест на добавление товара в корзину и проверить количество товара в корзине по апи

В помощь - https://github.com/autotests-cloud/example_project

В качестве ответа на задание приложить ссылку на репозиторий в GitHub

## Steps
___
### Part 1.
1. Created a structure of the project: created files .gitignore, build.gradle and me, directories.
2. Creaded several tests by lesson video (package `example`).
3. Created package 'ru.zenicko.tests.reqres' and test class `ReqresTests`.
4. Created 5 tests
   1. List users `/api/users?page=2`: check status code.
   2. Single user `/api/users/<n>`: check a value `<n>` of a field `id` response.
   3. Single user `/api/users/<n>`: check existing field.
   4. Single <resource> not found `/api/unknown/23`: check body is empty.
   5. Create `/api/users`: validation response JSON by schema.json.

### Part 2.
1. Created class `ApiSteps.java` which contains base api methods for tests.  
2. Created class `UserData` which contains methods converting registration data.
   1. Method `setUsedData` : Make request string by email, password and status "remember me".
   2. Method `getEmailUser`: Get email from a request string. 
   3. Get-method `getUserData`: Get a request string. 
   4. Field `userData` : Story the default data string in a request. 
      `--data Email=timrode%40mail.com&Password=123456&RememberMe=false`
   See 2 chart "About api site"
3. Creaded interface `NewUser` which uses library `Owner`. 
The properties of a new user is found in `resources/newuser.properties`.

## Quick start to use
1. Check data about new user in `resources/newuser.properties`.
2. Check how many cards in basket users `timrode@mail.com`. Delete exist cards.
   Or you can create a new user.
3. Start test (local) in command line
   `./gradlew test`

## About api site
1. Curl bush request "Registrate a new user"
   ```
   curl
   -H "Host: demowebshop.tricentis.com"
   -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0"
   -H "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8"
   -H "Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3"
   -H "Origin: http://demowebshop.tricentis.com"
   -H "Referer: http://demowebshop.tricentis.com/register"
   -H "Cookie: ARRAffinity=55622bac41413dfac968dd8f036553a9415557909fd0cd3244e7e0e656e4adc8;
   Nop.customer=c777ae0a-880a-4711-aa65-4a3f9791037b;
   ASP.NET_SessionId=tya3vkouq2llwqtezi3ihoyc;
   __RequestVerificationToken=q7nkFSDV6urRERAb_uvrxOVR-Orczdg3UFwYNZuLgw8cQuhhk-u0ArodJXjtbx5X6GC2C1Ab3QyHIV22BcgpCQ2rMyJR4AaanKpXowXFDhc1"
   -H "Upgrade-Insecure-Requests: 1"
   -H "Pragma: no-cache"
   -H "Cache-Control: no-cache"
   --data "__RequestVerificationToken=P_a2x7w9OUV3YCBrY2boYs4HaNaZa3AbylGLlxATCkAl3y2YSnQt6ohDrLDKYWpqx8zGhso4GcCOFFcvKmh1Yi3c46UhLhsMw1RhbZdAVt81&Gender=M&FirstName=Tim&LastName=Rod&Email=timrode%40mail.com&Password=123456&ConfirmPassword=123456&register-button=Register"
   --compressed "http://demowebshop.tricentis.com/register"
   ```
2. Curl bush request "Sign in"
   ```
   curl 
   -H "Host: demowebshop.tricentis.com" 
   -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0" 
   -H "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8" 
   -H "Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3" 
   -H "Origin: http://demowebshop.tricentis.com" 
   -H "Referer: http://demowebshop.tricentis.com/login" 
   -H "Cookie: ARRAffinity=55622bac41413dfac968dd8f036553a9415557909fd0cd3244e7e0e656e4adc8; 
   Nop.customer=35a01336-7f9f-4ef2-a820-f49d3253faf2; ASP.NET_SessionId=tya3vkouq2llwqtezi3ihoyc" 
   -H "Upgrade-Insecure-Requests: 1" 
   -H "Pragma: no-cache" 
   -H "Cache-Control: no-cache" 
   --data "Email=a%40a.ru&Password=123456&RememberMe=false" 
   --compressed "http://demowebshop.tricentis.com/login"
   ```
3. Curl bush request "Add a cards" 
   ```  
   curl 
   -H "Host: demowebshop.tricentis.com" 
   -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0" 
   -H "Accept: */*" 
   -H "Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3" 
   -H "X-Requested-With: XMLHttpRequest" 
   -H "Origin: http://demowebshop.tricentis.com" 
   -H "Referer: http://demowebshop.tricentis.com/" 
   
   -H "Cookie: ARRAffinity=55622bac41413dfac968dd8f036553a9415557909fd0cd3244e7e0e656e4adc8; 
   Nop.customer=c777ae0a-880a-4711-aa65-4a3f9791037b; 
   SAP.NET_SessionId=tya3vkouq2llwqtezi3ihoyc; 
   __RequestVerificationToken=q7nkFSDV6urRERAb_uvrxOVR-Orczdg3UFwYNZuLgw8cQuhhk-u0ArodJXjtbx5X6GC2C1Ab3QyHIV22BcgpCQ2rMyJR4AaanKpXowXFDhc1; 
   NOPCOMMERCE.AUTH=6BA58CC36EBDA17B2C675C1AFA873D3B20E5FDBBF86E045F509A6F6D0D26A0E272E4CA6A944F48DD0B45EE25F302DD57417D7912A364534642B92C7D3260F5EE6DE3628E22FAC2A161AFB7F88A2F12D23DA1C8393F64EF547F096C7C9E224B4E73FD2F3FBB51714DB43DA3202B1718F5DC32872C3C82B9B9B010230461057D13F3900D1D3A000B855B4DB5D210F88725" 
   
   -H "Pragma: no-cache" 
   -H "Cache-Control: no-cache" 
   --data-binary "" 
   --compressed "http://demowebshop.tricentis.com/addproducttocart/catalog/31/1/1"
   ``` 

4. Existing user.
   
   Logins:  default login timrode@mail.com
   a@a.ru, a1@a.ru, a10@a.ru,
   password = "123456".


## Resources
___
1. [Site the library `Rest-assured`](https://rest-assured.io/)
2. [The test stand](https://reqres.in/)
3. [URL status Selenoid site](https://selenoid.autotests.cloud/status)
4. [Status codes of HTTP response](https://bertal.ru/)
5. [Framework Hamcrest](http://hamcrest.org/JavaHamcrest/tutorial)
6. JSON
   1. [Как проверить JSON-объект в Java?](https://overcoder.net/q/714825/%D0%BA%D0%B0%D0%BA-%D0%BF%D1%80%D0%BE%D0%B2%D0%B5%D1%80%D0%B8%D1%82%D1%8C-json-%D0%BE%D0%B1%D1%8A%D0%B5%D0%BA%D1%82-%D0%B2-java)

## Miscellaneous
1. [REST Assured: что мы узнали за пять лет использования инструмента](https://habr.com/ru/company/dins/blog/464225/)
2. [A self-contained hamcrest jar containing all of the sub-modules in a single artifact.](https://mvnrepository.com/artifact/org.hamcrest/hamcrest-all)
3. [Куки - информация, которую веб-сайты хранят на вашем компьютере](https://support.mozilla.org/ru/kb/kuki-informaciya-kotoruyu-veb-sajty-hranyat-na-vas)



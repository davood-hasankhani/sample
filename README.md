# springboot-app
this spring boot web server, jpa, MySql

how to use : 
Run DemoSpringBootRestApplication
this urls:
http://localhost:8080/demo/all ==> show all users
http://localhost:8080/demo/add?username={user1}&password={password1}  ==> add {user1} and {password1}
http://localhost:8080/demo/delete?id={param1}   ==>> delete user with {param1} id
http://localhost:8080/demo/update?id={id}&username={editu}&password={editp} ==>> update user and pass with id={id}

http://localhost:8080/demo/profile/all   ==>> list all profile
http://localhost:8080/demo/profile/add?userid={id}&name={name}&phone={phone}&address={address} ==>>add profile
http://localhost:8080/demo/profile/update?profileid={id}&name={name}&phone={phone}&address={address} ==>>update profile
http://localhost:8080/demo/profile/delete?id={pid}  ==>> delete profile

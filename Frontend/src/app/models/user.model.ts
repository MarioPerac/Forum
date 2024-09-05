
export class User {
    name: string;
    surname: string;
    password: string;
    username: string;
    mail: string;
    role?: string;
    activated?: boolean;


    constructor(firstName: string, lastName: string, username: string, password: string, mail: string) {
        this.name = firstName;
        this.surname = lastName;
        this.password = password;
        this.username = username;
        this.mail = mail;
    }
}
export class Comment {
    id: number
    content: string;
    username: string;
    dateTime: string;

    constructor(id: number, content: string, answer: string, userUsername: string, date: string) {
        this.content = content;
        this.username = userUsername;
        this.id = id;
        this.dateTime = date;
    }
}
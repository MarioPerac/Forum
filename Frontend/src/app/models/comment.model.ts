export class Comment {
    id: number
    content: string;
    username: string;
    dateTime: string;
    published: boolean;
    forbidden: boolean;

    constructor(id: number, content: string, answer: string, userUsername: string, date: string, published: boolean, forbidden: boolean) {
        this.content = content;
        this.username = userUsername;
        this.id = id;
        this.dateTime = date;
        this.published = published;
        this.forbidden = forbidden;
    }
}
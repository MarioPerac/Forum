import { StickyDirection } from "@angular/cdk/table";

export class CommentRequest {
    content: string;
    username: string;
    areaId: number;

    constructor(content: string, username: string, areaId: number) {
        this.content = content;
        this.username = username;
        this.areaId = areaId;
    }
}
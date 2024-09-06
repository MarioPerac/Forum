import { StickyDirection } from "@angular/cdk/table";

export class CommentRequest {
    content: string;
    areaId: number;

    constructor(content: string, areaId: number) {
        this.content = content;
        this.areaId = areaId;
    }
}
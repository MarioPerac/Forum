export enum Roles {
    ADMIN = 1,
    USER = 2,
    MODERATOR = 3
}

export function getRoleId(name: string): number {

    if ("ADMIN" == name)
        return Roles.ADMIN;
    else if ("USER" == name)
        return Roles.USER;
    else if ("MODERATOR" == name)
        return Roles.MODERATOR;

    return 0;
}
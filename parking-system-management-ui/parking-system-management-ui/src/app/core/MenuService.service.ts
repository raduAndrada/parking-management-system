import { Inject, Injectable } from "@angular/core";

const socialLinks = [
    {key: "twitter", value: "https://twitter.com/LeviathanTlg", iconClass: "fab fa-twitter", tooltip: "Follow us on Twitter"},
];


const menuLinks = [
    {key: "Home", value: "/home", iconClass: ['fas', 'home']},
    {key: "Customers" , value: "/customers", iconClass: ['fas', 'users']},
    {key: "Parkings" , value: "/parkings", iconClass: ['fas', 'parking']},
];

@Injectable()
export class MenuService {

    public getMenuLinks(item?: string) {
        if (item) {
            // calling filter so we return an array
            return menuLinks.filter(e => e.key === item);
        }
        return menuLinks;
    }


    public getSocialLinks(item?: string) {
        if (item) {
            return socialLinks.filter(e => e.key === item);
        }
        return socialLinks;
    }
}

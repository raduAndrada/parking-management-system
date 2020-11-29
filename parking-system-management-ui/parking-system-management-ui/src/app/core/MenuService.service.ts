import { Inject, Injectable } from "@angular/core";

const contentLinks = [
    {key: "Gwent Meta", value: "/gwent-meta"},
    {key: "Gwent News", value : "/gwent-news"},
    {key: "Gwent Guides", value: "/gwent-guides"}
];

const teamsLinks = [
    {key: "Content Team", value: "/content-team"},
    {key: "Pro Team", value: "/pro-players"},
    {key: "Streamers", value: "/streamers"},
    {key: "Founders", value: "/founders"},
];

const socialLinks = [
    {key: "twitter", value: "https://twitter.com/LeviathanTlg", iconClass: "fab fa-twitter-square fa-lg", tooltip: "Follow us on Twitter"},
    {key: "discord" , value: "https://discord.gg/aGCmeB", iconClass: "fab fa-discord fa-lg", tooltip: "Join our discord"},
    {key: "twitch", value: "https://www.twitch.tv/teamleviathangaming", iconClass: "fab fa-twitch fa-lg", tooltip: "Check us on Twitch"},
    {key: "youtube", value: "https://www.youtube.com/channel/UCJNDW1wZjPQHu1nDM5TYI6w/featured",
                     iconClass: "fab fa-youtube fa-lg", tooltip: "Check us on Youtube"},
];


const menuLinks = [
    {key: "Home", value: "/home", iconClass: "fa fa-home"},
    {key: "Media" , value: "/media", iconClass: "fas fa-video"},
  //  {key: "Contact" , value: "/contact", iconClass: "fas fa-hand-holding-heart"},
 //   {key: "Sponsors", value: "/sponsors", iconClass: "fas fa-hand-holding-usd"}
];

const menuDropdowns = [
    {key: "Content", value: "/gwent-content", iconClass: "fas fa-chart-line", submenu: contentLinks},
    {key: "Teams" , value: "/teams", iconClass: "fas fa-user-friends", submenu: teamsLinks},
];


@Injectable()
export class MenuService {

    public getDropdowns(item?: string) {
        if (item) {
            // calling filter so we return an array
            return menuDropdowns.filter(e => e.key === item);
        }
        return menuDropdowns;
    }

    public getMenuLinks(item?: string) {
        if (item) {
            // calling filter so we return an array
            return menuLinks.filter(e => e.key === item);
        }
        return menuLinks;
    }

    public getContentLinks(item?: string) {
        if (item) {
            return contentLinks.find(e => e.key === item);
        }
        return contentLinks;
    }

    public getTeamLinks(item?: string) {
        if (item) {
            return teamsLinks.find(e => e.key === item);
        }
        return teamsLinks;
    }

    public getSocialLinks(item?: string) {
        if (item) {
            return socialLinks.filter(e => e.key === item);
        }
        return socialLinks;
    }
}

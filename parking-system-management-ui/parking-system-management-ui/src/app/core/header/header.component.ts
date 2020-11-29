import { Component, OnInit, HostListener } from '@angular/core';
import { MenuService } from '../MenuService.service';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import {faHome, faUsers, faParking} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [MenuService]
})
export class HeaderComponent implements OnInit {

  public collapsed = true;

  public menuItems = [];
  public socialItems = [];
  public menuDropdowns = [];
  public scrolledDown = false;

  constructor(
      private readonly menuService: MenuService,
     // public readonly authService: AuthService,
     private library: FaIconLibrary
  ) { 
    library.addIcons(faHome, faUsers, faParking);
  }

  ngOnInit() {
    this.menuItems = this.menuService.getMenuLinks();
    this.socialItems = this.menuService.getSocialLinks();
  }

  // public logout() {
  //   this.authService.logout();
  // }

  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

  @HostListener("window:scroll", [])
  onWindowScroll() {

    const number = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    if (number > 500) {
      this.scrolledDown = true;
    } else {
      this.scrolledDown = false;
    }

  }


}

import { Component, OnInit, Input, ViewChild, ContentChild } from "@angular/core";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";



@Component({
  selector: 'confirmation-modal',
  templateUrl: './confirmation-modal.component.html',
  styleUrls: ['./confirmation-modal.component.css']
})
export class ConfirmationModalComponent implements OnInit {
  @ViewChild("content") content;

  @Input() confirmationMessage: string;
  @Input() changedName: string;

  public modalRef: NgbModalRef;
  public closeResult: string;


  constructor(private modalService: NgbModal) { }

  ngOnInit() {
  }

  public open() {
    return this.modalService.open(this.content,  { windowClass: "dark-modal", size: "lg" });

  }


}

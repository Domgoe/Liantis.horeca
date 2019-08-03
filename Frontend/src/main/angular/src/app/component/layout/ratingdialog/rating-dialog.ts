import { Component, Inject, OnInit} from '@angular/core';
import { Horeca} from "../../../model/Horeca";
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup} from "@angular/forms";


//Modal dialog for updating rating
@Component({
  selector: 'rating-dialog',
  templateUrl: 'rating-dialog.html',
})
export class RatingDialogComponent implements OnInit{

  form: FormGroup;
  Arr: Array<number>;
  selectedRating: number;
  numberOfStars: number = 5;

  constructor(
    private fb: FormBuilder, public dialogRef: MatDialogRef<RatingDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Horeca) {

    this.form = fb.group({
      rating: [data.rating]
    });

  }

  ngOnInit(): void {
    /**TODO: BUG => reeds toekende sterren van horeca-zaak tonen bij het openen van de Opendialog box.
     * Voorlopige oplossing: reeds toegekende sterren getoond in aparte paragraaf
     */
    this.form.get('rating').setValue(this.form.value);
  }

  closeRatingDropDown() {
    this.selectedRating = +this.form.value["rating"];
    this.Arr = Array(this.selectedRating);
  }

  closeDialog() {
    this.dialogRef.close();
  }

  saveDialog(){
    this.dialogRef.close(this.form.value);
  }

}

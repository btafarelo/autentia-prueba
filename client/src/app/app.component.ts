import { Component } from '@angular/core';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Autentia Prueba';
  h1 = 'Catálogo de Cursos';

  constructor() {}
  
}

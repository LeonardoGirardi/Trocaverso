import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {


  searchQuery = '';

  // ⚠️ Mock temporário. Substituir após integrar autenticação com API.
  user = {
    name: 'Leonardo Girardi' // mockado
  };

  // ⚠️ Mock de contadores — substituir por dados reais da API
  notificationCount = 3;
  proposalCount = 2;
  showDropdown: any;

  onLogout() {

  }

  toggleDropdown() {

  }

  getUserInitials() {
    return "";
  }

  onSearch() {

  }
}




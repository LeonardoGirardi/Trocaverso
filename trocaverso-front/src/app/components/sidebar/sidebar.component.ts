import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

interface SidebarItem {
  icon: string;
  label: string;
  route: string;
  badge?: number;
}

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  sidebarItems: SidebarItem[] = [
    { icon: '🏠', label: 'Feed Principal', route: '/' },
    { icon: '📦', label: 'Meus Itens', route: '/my-items' },
    { icon: '🔧', label: 'Meus Serviços', route: '/my-services' },
    { icon: '📤', label: 'Propostas Enviadas', route: '/sent-proposals', badge: 2 },
    { icon: '📥', label: 'Propostas Recebidas', route: '/received-proposals', badge: 3 },
    { icon: '⭐', label: 'Minhas Avaliações', route: '/evaluations' },
    { icon: '👤', label: 'Meu Perfil', route: '/profile' },
    { icon: '🔍', label: 'Explorar', route: '/explore' }
  ];
}

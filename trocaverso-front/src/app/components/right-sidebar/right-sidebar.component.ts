import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

interface Suggestion {
  id: string;
  name: string;
  description: string;
  avatar: string;
}

interface Activity {
  id: string;
  name: string;
  description: string;
  avatar: string;
  status: 'accepted' | 'pending' | 'rejected';
}

@Component({
  selector: 'app-right-sidebar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './right-sidebar.component.html',
  styleUrl: './right-sidebar.component.css'
})
export class RightSidebarComponent {
  suggestions: Suggestion[] = [
    {
      id: '1',
      name: 'Carlos Ferreira',
      description: 'Oferece serviços de design',
      avatar: 'CF'
    },
    {
      id: '2',
      name: 'Laura Mendes',
      description: 'Troca itens de decoração',
      avatar: 'LM'
    },
    {
      id: '3',
      name: 'Roberto Fonseca',
      description: 'Especialista em eletrônicos',
      avatar: 'RF'
    }
  ];

  recentActivities: Activity[] = [
    {
      id: '1',
      name: 'João Lima',
      description: 'Aceitou sua proposta',
      avatar: 'JL',
      status: 'accepted'
    },
    {
      id: '2',
      name: 'Mariana Figueira',
      description: 'Fez uma proposta',
      avatar: 'MF',
      status: 'pending'
    }
  ];

  onFollowUser(suggestionId: string): void {
    console.log('Seguindo usuário:', suggestionId);
    // Implementar lógica de seguir usuário
  }

  getStatusClass(status: string): string {
    return `status-${status}`;
  }

  getStatusText(status: string): string {
    const statusMap: { [key: string]: string } = {
      'accepted': 'Aceita',
      'pending': 'Pendente',
      'rejected': 'Rejeitada'
    };
    return statusMap[status] || status;
  }
}

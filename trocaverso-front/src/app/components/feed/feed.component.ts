import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface FeedItem {
  id: string;
  user: {
    name: string;
    avatar: string;
  };
  timestamp: string;
  content: {
    text: string;
    image?: string;
    type: 'item' | 'service' | 'general';
  };
  actions: {
    likes: number;
    isLiked: boolean;
  };
}

@Component({
  selector: 'app-feed',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.css'
})
export class FeedComponent implements OnInit {
  feedItems: FeedItem[] = [
    {
      id: '1',
      user: {
        name: 'Maria João',
        avatar: 'MJ'
      },
      timestamp: '2 horas atrás',
      content: {
        text: '🎸 Violão Yamaha em ótimo estado! Estou procurando trocar por um teclado ou equipamento de fotografia. Interessados, me mandem uma proposta!',
        image: '📸 Foto do Violão',
        type: 'item'
      },
      actions: {
        likes: 0,
        isLiked: false
      }
    },
    {
      id: '2',
      user: {
        name: 'Pedro Silva',
        avatar: 'PS'
      },
      timestamp: '5 horas atrás',
      content: {
        text: '💻 Ofereço serviços de programação web! Posso desenvolver sites, sistemas ou aplicativos em troca de aulas de inglês ou equipamentos de informática.',
        type: 'service'
      },
      actions: {
        likes: 0,
        isLiked: false
      }
    },
    {
      id: '3',
      user: {
        name: 'Ana Santos',
        avatar: 'AS'
      },
      timestamp: '1 dia atrás',
      content: {
        text: '📚 Tenho uma coleção de livros de ficção científica que gostaria de trocar por livros de culinária ou jardinagem. São mais de 20 títulos!',
        image: '📖 Foto dos Livros',
        type: 'item'
      },
      actions: {
        likes: 0,
        isLiked: false
      }
    }
  ];

  feedActions = [
    {
      icon: '❤️',
      text: 'Curtir',
      action: 'like'
    },
    {
      icon: '🔄',
      text: 'Propor Troca',
      action: 'propose'
    },
    {
      icon: '💬',
      text: 'Comentar',
      action: 'comment'
    }
  ];

  ngOnInit(): void {
    // Inicialização do componente
  }

  onFeedAction(action: string, feedItem: FeedItem): void {
    switch (action) {
      case 'like':
        this.toggleLike(feedItem);
        break;
      case 'propose':
        this.openProposalModal(feedItem);
        break;
      case 'comment':
        this.openCommentModal(feedItem);
        break;
      default:
        console.log('Ação não reconhecida:', action);
    }
  }

  private toggleLike(feedItem: FeedItem): void {
    feedItem.actions.isLiked = !feedItem.actions.isLiked;
    if (feedItem.actions.isLiked) {
      feedItem.actions.likes++;
    } else {
      feedItem.actions.likes--;
    }
  }

  private openProposalModal(feedItem: FeedItem): void {
    console.log('Abrindo modal de proposta para:', feedItem.user.name);
    // Implementar lógica para abrir modal de proposta
    alert('Funcionalidade de proposta será implementada!');
  }

  private openCommentModal(feedItem: FeedItem): void {
    console.log('Abrindo modal de comentários para:', feedItem.user.name);
    // Implementar lógica para abrir modal de comentários
    alert('Funcionalidade de comentários será implementada!');
  }

  getLikeButtonText(feedItem: FeedItem): string {
    return feedItem.actions.isLiked ? '❤️ Curtido' : '❤️ Curtir';
  }

  getLikeButtonClass(feedItem: FeedItem): string {
    return feedItem.actions.isLiked ? 'feed-action liked' : 'feed-action';
  }

  trackByFeedItem(index: number, item: FeedItem): string {
    return item.id;
  }

  trackByAction(index: number, action: any): string {
    return action.action;
  }
}

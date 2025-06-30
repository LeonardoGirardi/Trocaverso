import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

export interface PostAction {
  type: 'item' | 'service' | 'post';
  data?: any;
}

@Component({
  selector: 'app-post-creator',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './post-creator.component.html',
  styleUrl: './post-creator.component.css'
})
export class PostCreatorComponent {
  @Output() openItemModal = new EventEmitter<void>();
  @Output() openServiceModal = new EventEmitter<void>();
  @Output() openPostModal = new EventEmitter<void>();

  userAvatar: string = 'LG';
  placeholderText: string = 'O que você gostaria de trocar hoje?';

  postActions = [
    {
      icon: '📦',
      text: 'Adicionar Item',
      action: 'item'
    },
    {
      icon: '🔧',
      text: 'Adicionar Serviço',
      action: 'service'
    }
  ];

  onPostInputClick(): void {
    this.openPostModal.emit();
  }

  onActionClick(actionType: string): void {
    switch (actionType) {
      case 'item':
        this.openItemModal.emit();
        break;
      case 'service':
        this.openServiceModal.emit();
        break;
      default:
        console.log('Ação não reconhecida:', actionType);
    }
  }

  trackByAction(index: number, action: any): string {
    return action.action;
  }
}

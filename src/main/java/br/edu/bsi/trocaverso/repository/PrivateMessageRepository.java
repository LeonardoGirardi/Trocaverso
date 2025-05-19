package br.edu.bsi.trocaverso.repository;

import br.edu.bsi.trocaverso.model.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, UUID> {
    List<PrivateMessage> findBySenderIdAndReceiverIdOrReceiverIdAndSenderIdOrderByDateAsc(UUID senderId, UUID receiverId, UUID senderId1, UUID receiverId1);
}

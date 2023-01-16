package com.ecommerce.enkabutikiw.Serviceimpl;

import com.ecommerce.enkabutikiw.models.Jaime;
import com.ecommerce.enkabutikiw.payload.response.MessageResponse;
import com.ecommerce.enkabutikiw.repository.JaimeRepository;
import com.ecommerce.enkabutikiw.services.JaimeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JaimeServiceimpl implements JaimeService {

    @Autowired
    private  final JaimeRepository jaimeRepository;

    @Override
    public Jaime ajouterJaime(Jaime jaime, Long  user_id, Long boutique_id) {


        return   jaimeRepository.save(jaime);

    }

    @Override
    public MessageResponse supprimerJaime(Long id) {
        Optional<Jaime> jaime = this.jaimeRepository.findById(id);
        if (!jaime.isPresent()){
            MessageResponse message = new MessageResponse(" J'aime non trouvée ");
            return message;

        }else {
            this.jaimeRepository.delete(jaime.get());
            MessageResponse message = new MessageResponse("J'aime supprimé avec succès");

            return message;
        }
    }

    @Override
    public List<Jaime> liste() {
        return jaimeRepository.findAll();
    }
}

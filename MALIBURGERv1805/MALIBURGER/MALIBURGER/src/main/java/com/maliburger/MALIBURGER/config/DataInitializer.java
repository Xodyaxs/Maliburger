package com.maliburger.MALIBURGER.config;

import com.maliburger.MALIBURGER.Model.Malibur;
import com.maliburger.MALIBURGER.Repository.MaliRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner{

    private final MaliRepository maliRepository;

    //Acá se pide la herramienta para comunicarse con la base de datos.
    public DataInitializer(MaliRepository maliRepository){
        this.maliRepository = maliRepository;
    }

    //Método que se activa sólo para iniciar.
    @Override
    public void run(String... args) throws Exception{
        //Revisa que hayan hamburguesas, si es que hay 0, no hay hamburguesas.
        if (maliRepository.count()==0) {

            //Lista de las hamburguesas
            List<Malibur> burgersBase = List.of(
                    new Malibur(null, 9490, "Hamburguesa de 150 gramos con queso cheddar y tocino. ", "La Chis. ", "Vacuno. "),
                    new Malibur(null, 15490, "Hamburguesa de 150 gramos con doble queso cheddar, tostitos, ají jalapeño en rodajas, salsa malibú y guacamole. ", "La Maliburger. ", "Vacuno. "),
                    new Malibur(null, 10490, "Apanado de pechuga de pollo con mayonesa, tomate en rodajas, salsa malibú y palta. ", "La italiana chihuahueña. ", "Pollo. "),
                    new Malibur(null, 12990, "Hamburguesa de soja con salsa malibú, lechuga y tomate en rodajas. ", "La Maliplanta. ", "Vegetal. ")
                    );
            //Se guardan las hamburguesas en la base de datos.
            maliRepository.saveAll(burgersBase);
            System.out.println("¡Menú de maliburgers pre-cargados con éxito en la base de datos! ");
        }else{
            //Si hay hamburguesas, no se duplican.
            System.out.println("La base de datos ya contiene maliburgers. No se han cargado nuevos datos. ");
        }
    }

}



package Search;

import java.util.Random;

/**
 * DNADataGenerator
 *
 * Genera secuencias de ADN para pruebas experimentales.
 *
 * Se utiliza para comparar:
 *
 * - Fuerza Bruta
 * - KMP
 *
 * Permitirá medir cantidad de comparaciones para:
 *
 * motivo longitud:
 *
 * 4
 * 10
 * 20
 *
 * secuencia:
 *
 * 1000 caracteres
 */

public class DNADataGenerator {

    private static final char[] BASES={
            'A',
            'C',
            'G',
            'T'
    };

    private static Random random=
            new Random();

    /*
    Genera secuencia ADN
    */

    public static String generateSequence(
            int length
    ){

        StringBuilder dna=
                new StringBuilder();

        for(
                int i=0;
                i<length;
                i++
        ){

            dna.append(
                    BASES[
                            random.nextInt(4)
                            ]
            );

        }

        return dna.toString();

    }

    /*
    Extrae un patron existente
    */

    public static String generatePattern(
            String sequence,
            int patternLength
    ){

        int start=
                random.nextInt(
                        sequence.length()
                                -
                                patternLength
                );

        return sequence.substring(
                start,
                start
                        +
                        patternLength
        );

    }

    /*
    Genera secuencia para experimento
    */

    public static String generateExperimentSequence(){

        return generateSequence(
                1000
        );

    }

    /*
    Genera motivos requeridos
    */

    public static String[] generatePatterns(
            String sequence
    ){

        String[] patterns=
                new String[3];

        patterns[0]=
                generatePattern(
                        sequence,
                        4
                );

        patterns[1]=
                generatePattern(
                        sequence,
                        10
                );

        patterns[2]=
                generatePattern(
                        sequence,
                        20
                );

        return patterns;

    }

}


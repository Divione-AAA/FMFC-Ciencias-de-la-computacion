package Search;

/**
 * Implementacion fuerza bruta.
 *
 * Busca caracter por caracter.
 *
 * Complejidad:
 *
 * O(n*m)
 */

public class BruteForceSearch
        implements DNAPatternSearch{

    @Override
    public SearchResult search(
            String T,
            String P
    ){

        SearchResult r=
                new SearchResult(
                        "Fuerza Bruta"
                );

        if(
                P.length()
                        >
                        T.length()
        ){

            return r;

        }

        int n=
                T.length();

        int m=
                P.length();

        int j;

        for(
                int i=0;
                i<=n-m;
                i++
        ){

            j=0;

            while(
                    j<m
            ){

                r.agregarComparacion();

                if(
                        T.charAt(i+j)
                                !=
                                P.charAt(j)
                ){

                    break;

                }

                j++;

            }

            if(
                    j==m
            ){

                r.agregarPosicion(
                        i
                );

            }

        }

        return r;

    }

}
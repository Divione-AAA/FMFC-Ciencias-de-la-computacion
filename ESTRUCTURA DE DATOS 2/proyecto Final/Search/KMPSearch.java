package Search;

/**
 * Implementacion KMP.
 *
 * Utiliza funcion de falla.
 *
 * Complejidad:
 *
 * O(n+m)
 */

public class KMPSearch
        implements DNAPatternSearch{

    public int[] funcionFalla(
            String P
    ){

        int[] falla=
                new int[
                        P.length()
                        ];

        falla[0]=0;

        int j=0;

        int i=1;

        while(
                i<P.length()
        ){

            if(
                    P.charAt(j)
                            ==
                            P.charAt(i)
            ){

                falla[i]=j+1;

                i++;

                j++;

            }
            else if(
                    j>0
            ){

                j=
                        falla[
                                j-1
                                ];

            }
            else{

                falla[i]=0;

                i++;

            }

        }

        return falla;

    }

    @Override
    public SearchResult search(
            String T,
            String P
    ){

        SearchResult r=
                new SearchResult(
                        "KMP"
                );

        if(
                P.length()
                        >
                        T.length()
        ){

            return r;

        }

        int[] fail=
                funcionFalla(
                        P
                );

        int n=
                T.length();

        int m=
                P.length();

        int i=0;

        int j=0;

        while(
                i<n
        ){

            r.agregarComparacion();

            if(
                    P.charAt(j)
                            ==
                            T.charAt(i)
            ){

                if(
                        j
                                ==
                                m-1
                ){

                    r.agregarPosicion(
                            i-m+1
                    );

                    j=
                            fail[j];

                    i++;

                }
                else{

                    i++;

                    j++;

                }

            }
            else if(
                    j>0
            ){

                j=
                        fail[
                                j-1
                                ];

            }
            else{

                i++;

            }

        }

        return r;

    }

}
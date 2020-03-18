package ru.itvitality.sbrf.cu.library.entities.impl;

public enum Shelf {
/*    A('a'),B('b'),C('c'),D('d'),
    E('e'),F('f'),G('g'),H('h'),
    I('i'),J('j'),K('1'),L('l'),
    M('m'),N('n'),O('o'),P('p'),
    Q('q'),R('r'),S('s'),T('t'),
    U('u'),V('v'),W('w'),X('x'),
    Y('y'),Z('z');*/
    Q,W,E,R,T,Y,U,I,O,P,A,S,D,F,G,H,J,K,L,Z,X,C,V,B,N,M;
/*    private char authorsNamesFirstLetter;
    Shelf(char authorsNamesFirstLetter){
        this.authorsNamesFirstLetter=authorsNamesFirstLetter;
    }*/
    static public Shelf defineBookShelf(String author){
        switch (author.toLowerCase().charAt(0)){
            case 'a': return A;
            case 'b': return B;
            case 'c': return C;
            case 'd': return D;
            case 'e': return E;
            case 'f': return F;
            case 'g': return G;
            case 'h': return H;
            case 'i': return I;
            case 'j': return J;
            case 'k': return K;
            case 'l': return L;
            case 'm': return M;
            case 'n': return N;
            case 'o': return O;
            case 'p': return P;
            case 'q': return Q;
            case 'r': return R;
            case 's': return S;
            case 't': return T;
            case 'u': return U;
            case 'v': return V;
            case 'w': return W;
            case 'x': return X;
            case 'y': return Y;
            case 'z': return Z;
            default:  return null;
        }
    }
}

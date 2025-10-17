#include<bits/stdc++.h>
using namespace std;

#define loop(i,n) for(int i=0;i<n;i++)
#define loopi(i,n) for(int i=0;i<=n;i++)
#define lup(i,x,n) for(int i=x;i<=n;i++)
#define loup(i,x,n) for(int i=x;i<n;i++)
#define fast ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
#define ll long long
#define int long long
#define double long double
#define endl '\n'
#define over(i,a) for(auto i: a)
#define F first
#define S second
#define MP make_pair
#define PB push_back
#define PF push_front
#define INS insert
#define sortt(a) sort(a.begin(),a.end())

const int INF=4294967296;
const int _INF=-4294967296;
const double pi=3.1415926535;

//clase abstracta padre
class Investigador {
    protected:
        double salario, AC;
        string nombre,ID;
        bool esInv=false;
        vector<string> bec;
    public:
        Investigador(string nombre,string id,double salario, bool es){
            this->salario=salario;
            this->nombre=nombre;
            this->ID=id;
            this->esInv=es;
        }

        string getId() {
            return ID;
        }

        Investigador(){}

        double getAC(){
            return AC;
        }

        string getNombre() {
            return nombre;
        }
        //funcion para en caso de tener becarios asociados anadirselos
        void addBec(string n) {
            if(esInv==true) {
                bec.PB(n);
            }else {
                perror("Es un becario");
            }
        }

        //obtener lista de becarios en caso de no ser uno
        vector<string> getBec() {
            return bec;
        }

        bool operator<(Investigador &other){
            return AC<other.AC;
        }

        bool operator>(Investigador &other) {
            return AC>other.AC;
        }

        double getSalario() {
            return salario;
        }
        //funcion abstracta que se define en las clses hijas
        virtual double calcularAC()=0;
};

class Senior: public Investigador {
    private:
        string area;
    public:
    Senior(string area,string nombre,string id, double salario):Investigador(nombre,id,salario,true) {
        this->area=area;
    }
    double calcularAC()override{
        return  0*45*(salario*0.20);

    }
    //constructor generico
    Senior(){
        
    }
};

class Asociado: public Investigador {
    private:
        int publicaciones;
    public:
        Asociado(int publicaciones,string nombre,string id, double salario):Investigador(nombre,id,salario,true) {
            this->publicaciones=publicaciones;
        }
        double calcularAC()override{
            return 0.30*(0*10*salario)*(1*(publicaciones/100));
        }
        Asociado(){
        }
};

class Becario: public Investigador {
    private:
        vector<double> salarios;
        //para en caso de tener un tutor de algun tipo
        Asociado tutor;
        Senior tutors;
        double prom() {
            double ans=0;
            over(i,salarios)
                ans+=i;
            return ans/salarios.size();
        }

    public:
        //en caso de tener tutor, uno de cada tipo
        Becario(vector<double> salarios,Asociado tutor, string nombre, string id, double s) : Investigador(nombre, id, s, false){
            this->salarios = salarios;
            this->tutor = tutor;
        }
        Becario(vector<double> salarios,Senior tutor, string nombre, string id, double s) : Investigador(nombre, id, s, false){
            this->salarios = salarios;
            this->tutors = tutor;
        }
        //constructor sin tutor incluido
        Becario(vector<double> salarios, string nombre, string id, double s) : Investigador(nombre, id, s, false){
            this->salarios = salarios;
        }
        double calcularAC()override{
            return AC= 0.15*(0.08*prom());
        }
};

int32_t main(){

    fast
    //creo una lista de Investigadores
    vector<Investigador*> lista;
    //anado un conjunto de estos
    lista.PB(new Asociado(12,"Hector","68541",900));
    lista.PB(new Senior("IA","David","987198",700));
    lista.PB(new Becario({10,20}, lista[0], "George","651564",800));
    //segun el id lo localizo y lo borro
    string id;
    getline(cin,id);
    for (int i = 0;i<lista.size();i++) {
        if (lista[i]->getId()==id){
            lista.erase(lista.begin()+i);
            cout<<"Borrado existosamente"<<endl;
        }
    }
    //Los ordeno segun su AC
    for (int i = 0; i < lista.size(); i++) {
        for (int j = 0; j < lista.size()-1;j++) {
                if (lista[j]->getAC()>lista[j+1]->getAC()) {
                    swap(lista[j],lista[j+1]);
                }
        }
    }
    cout<<"El trabajador que mas aporta es: "<<lista[0]->getNombre()<<endl;
    cout<<"El trabajador que menos aporta es: "<<lista[lista.size()-1]->getNombre()<<endl;
    double ans=0;
    loop(i,lista.size()) {
        ans+=lista[i]->calcularAC();
    }
    cout<<"El aporte del grupo es de: "<<ans<<endl;

    string inv;
    getline(cin, inv);
    cout<<"Los becarios con este investigador son: "<<endl;
    loop(i,lista.size()) {
        if (lista[i]->getNombre().compare(inv)) {
            over(i,lista[i]->getBec()) {
                cout<<i<<" ";
            }
            cout<<endl;
        }
    }
return 0;}

// Nota 3
// Errores encontados
// - No devuelve los becarios asociados a un tutor
// - No uso una clase para el sistema de amnera general

#include<bits/stdc++.h>
using namespace std;

int main(){

    vector<vector<int>> g(100);
    int n,m;

    cin>>n>>m;

    for(int i=0;i<m;i++){
        int a,b;
        cin>>a>>b;
        g[a].push_back(b);
        g[b].push_back(a);
    }

    for(auto v:g){
        for(auto u:v){
            cout<<u<<" ";
        }
        cout<<endl;
    }

return 0;}
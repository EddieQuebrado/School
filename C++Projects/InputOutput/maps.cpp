#include <bits/stdc++.h>

using namespace std;

bool exists(map<string,int> m, string s){
	if(m.count(s)){
		return true;
	}
	return false;
}
int main(){
	map<string, int> m;
	m["monkey"] = 5;
	m["lemur"] = 4;
	m["chimpanzee"] = 1;
	
	string s;
	cout << "What is the name of one of the most beloved bipedal animals such that they stick to groups and eat banannas. (hint: one even went to space :)" << "\n";
	cin >> s;
	if(!exists(m,s)){
		cout << "that is not the correct answer" << "\n";
	} else {
		cout << "that is correct" << "\n";
	}
}		

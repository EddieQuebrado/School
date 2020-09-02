#include <bits/stdc++.h>

using namespace std;


bool comp(string a, string b){
	if(a.size() != b.size()) return a.size() < b.size();
	return a < b;
}

int main(){
	vector<int> v = {4,2,5,3,5,8,3};
	sort(v.begin(),v.end());
	for(int e: v)
		cout << e << "\n";
	sort(v.rbegin(),v.rend());
	cout << "reversed order" << "\n";
	for(int e: v)
		cout << e << "\n";
	
	int n;
	cout << "Enter an integer value for the size of an array:";
	cin >> n;
	cout << "Enter values for the array of size " << n << "\n";
	int a[n];
	for(int i = 0; i < n; i++){
		cin >> a[i];
	}
	cout << "Sorting the given array of size " << n << "\n";
	sort(a, a+n);
	for(int e: a)
		cout << e << "\n";


	string s;
	cout << "Enter in a string literal: " << "\n";
	cin >> s;
	cout << "String literal: " << s << "\n";
	cout << "Sorted String: ";
	sort(s.begin(), s.end());
	for(char c: s)
		cout << c << "\n";

	string s1, s2;
	cout << "Enter in two strings: " << "\n";
	cin >> s1 >> s2;
	if(comp(s1,s2)){
		cout << "Same size strings" << "\n";
	} else {
		cout << "Two different sized strings" << "\n";
	}
}

struct P {
	int x, y;
	bool operator<(const P &p) {
		if(x != p.x) return x < p.x;
		else return y < p.y;
	}
};

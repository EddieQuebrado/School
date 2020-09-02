#include <bits/stdc++.h>

using namespace std;

int main(){
	set<int> s;
	for(int i = 0; i < 10; i++){
		s.insert(i);
	}
	int x;
	int size = s.size();
	cout << "Enter a number to erase from set of size " << size << x << "\n";
	cin >> x;
	s.erase(s.find(x));
	for(auto e: s)
		cout << e << " ";
}

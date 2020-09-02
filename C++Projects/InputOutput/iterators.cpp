#include <bits/stdc++.h>

using namespace std;
void singleNumber(vector<int>& nums){
	int ans = 0;
	for(int a : nums){
		ans ^= a;
	}
	cout << ans << "\n";
}
int main(){
	set<int> s = { 12 , 24, 83, 23, 81, 23 , 11 };
	//auto it = s.begin();
	//cout << *it << "\n";
//	for(auto it = s.begin(); it != s.end(); it++){
//		cout << *it << "\n";
//	}
//	int x = 82;
//	auto it = s.lower_bound(x);
//	if(it == s.begin()){
//		cout << *it << "\n";
//	} else if (it == s.end()){
//		it--;
//		cout << *it << "\n";
//	} else {
//		int a = *it; it--;
//		int b = *it;
//		if(x-b < a-x) cout << b << "\n";
//		else cout << a << "\n";
//	}
	vector<int> v = {1, 1, 2 , 2, 4};
	singleNumber(v);
}

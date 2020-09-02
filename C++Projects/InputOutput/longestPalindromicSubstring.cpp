#include <bits/stdc++.h>
#include <string>
#include <iostream>
using namespace std;

#define ll long long
#define ar array

string longestPalindrome(string s){
	if(s.length() <= 1) return s;
	size_t start = 0, len = 0;
	for(size_t i = 0; i < s.length() && s.length() - i > len / 2;){
		size_t left = i, right;
		for(right = i + 1; right < s.length() && s[right] == s[right -1]; right++) {}
		for(i = right--; left > 0 && right < s.length() - 1 && s[left - 1] == s[right + 1]; left--, right++) {}
		auto _len = right - left + 1;
		if(_len > len){
			start = left;
			len = _len;
		}
	}
	return s.substr(start, len);
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);

	int t, i=1;
	cin >> t;
	string s = "aabab";
	string ans;
	for(int i = 1; i <= t; i++){
		cout << "Case #" << i << ": ";
		ans = longestPalindrome(s);
	}
	cout << ans << "\n";
}

#include <bits/stdc++.h>

using namespace std;

//instantiates another list
void moveZeros(vector<int>& nums){
	int soi = 0;
	vector<int> nv;
	for(unsigned int i = 0; i < nums.size(); i++){
		if(nums[i] > 0) nv.push_back(nums[i]);
		else soi++;
	}
	for(int i = 0; i < soi; i++){
		nv.push_back(0);
	}
	for(int a: nv){
		cout << a << "\n";
	}
}

void altMoveZeros(vector<int>& nums){
	int p = 0;
	for(unsigned int i = 0; i < nums.size(); i ++){
		if(nums[i]){
			swap(nums[i], nums[p++]);
		}
	}

	for(int i: nums){
		cout << i << "\n";
	}
}
int main(){
	vector<int> v = {0, 1, 0, 3, 12};
	//expected output 1 3 12 0 0
	altMoveZeros(v);
}

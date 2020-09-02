#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define ar array

double solve(vector<int>& num1, vector<int>& num2){
	if(num1.size() == 0 || num2.size()==0) return 0;
	vector<int> a;
	for(int e : num1)
		a.push_back(e);
	for(int e : num2)
		a.push_back(e);

	sort(a.begin(), a.end());
	if(a.size()%2 != 0)
		return (double)a[a.size() / 2];
	return (double) (a[(a.size()-1)/2] + a[a.size()/2]) / 2.0;
}

//double findMedianSortedArrays(int input1[], int input2[]){
//	if(input1.length > input2.length)
//		return findMedianSortedArrays(input2,input1);
//
//	int x = input1.length;
//	int y = input2.length;
//	int low = 0, high = x;
//	while(low <= high){
//		int partitionX = low(high-low)/2;
//		int partitionY = (x+y+1)/2 - partitionX;
//
//		int maxLeftX = (partitionX == 0) ? -1e19 : input1[partitionX-1];
//		int minRightX = (partitionX == x) ? 1e19 : input1[partitionX];
//
//		int maxLeftY = (partitionY == 0) ? -1e19 : input2[partitionY-1];
//		int minRightY = (partitionY == y) ? 1e19 : input2[partitionY];
//
//		if(maxLeftX <= minRightY && maxLeftY <= minRightX){
//			if((x+y)%2==0){
//				cout << "here" + "\n";
//			} else {
//				cout << "here" + "\n";
//			}
//		} else if (maxLeftX > minRightY){
//			high = partitionX - 1;
//		} else {
//			low = partitionX + 1;
//		}
//	}
//}

double fMSA(vector<int>& nums1, vector<int>& nums2){
	int N1 = nums1.size();
	int N2 = nums2.size();
	if(N1 < N2)
		return fMSA(nums2, nums1);

	int lo = 0, hi = N2 * 2;
	while(lo <= hi){
		int mid2 = lo + (hi - lo) / 2; //try cut 2
		int mid1 = N1 + N2 - mid2; //cut accordingly

		double L1 = (mid1 == 0) ? INT_MIN : nums1[(mid1-1)/2];
		double L2 = (mid2 == 0) ? INT_MIN : nums2[(mid2-1)/2];
		double R1 = (mid1 == N1 * 2) ? INT_MAX : nums1[(mid1)/2];
		double R2 = (mid2 == N2 * 2) ? INT_MAX : nums2[(mid2)/2];

		if(L1 > R2) lo = mid2 + 1; //A1's lower half is too big; need to move C1 left (C2 right)
		else if (L2 > R1) hi = mid2 - 1; //A2's lower half too big; need to move C2 left
		else return (max(L1,L2) + min(R1,R2)) / 2; //Otherwise, that's the right cut
	}
	return -1;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);

	int t, i=1;
	cin >> t;
	vector<int> num1 = {1,2,7,3,4,5,9};
	vector<int> num2 = {3,4,5,6,22,11};
	double ans;
	for(int i = 1; i <= t; i++){
		cout << "Case #" << i << ": ";
		ans = fMSA(num1, num2);
	}
	cout << ans << "\n";
}

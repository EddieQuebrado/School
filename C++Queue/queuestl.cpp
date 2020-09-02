/*
 * queuestl.cpp
 * 
 *  Created On: April 4th, 2020
 *      Author: Eddie Quebrado
 * 
 */

#include <iostream>
#include <queue>
using namespace std;

int main() {
    queue<int> myQueue;
    for(int i = 0; i < 10; i++){
        cout << "enQueueing: " << i << endl;
        myQueue.push(i);
    }

    cout << "size of my queue: " << myQueue.size() << endl;
    cout << "The back of my queue: " << myQueue.back() << endl;

    while(!myQueue.empty()){
        cout << "dequeueing: " << myQueue.front() << endl;
        myQueue.pop();
    }
}
#include <cstdio>
#include <cstdlib>
#include <iostream>
#include <cstring>
#include <string>
#include <algorithm>
#include <cmath>
#include <vector>
#include <queue>
#include <stack>
#include <deque>
#include <map>
#include <unordered_map>
#include <set>
#include <unordered_set>

using namespace std;
int n;
vector<int> tree;

map<int, int> mp;
int values[2][500010];

long long sum(int i){
    long long ans = 0;
    while(i > 0){
        ans += tree[i];
        i -= (i & -i);
    }
    return ans;
}
void update(int i, int diff){
    while(i < tree.size()){
        tree[i] += diff;
        i += (i & -i);
    }
}


int main() {
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);
    
    cin >> n;
    tree.resize(n+1);
    
    for (int i=1; i<=n; i++) {
        cin >> values[0][i];
    }
    for (int i=1; i<=n; i++) {
        cin >> values[1][i];
        mp[values[1][i]] = i;
    }
    
    
    long long res = 0;
    for(int i=1; i<=n; i++){
        res += sum(n-1) - sum(mp[values[0][i]]);
        update(mp[values[0][i]], 1);
    }
    cout << res;
    
    
    
    return 0;
}

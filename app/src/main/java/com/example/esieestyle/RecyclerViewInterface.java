package com.example.esieestyle;

import com.google.firebase.firestore.DocumentSnapshot;

public interface RecyclerViewInterface {
    void OnItemClick(DocumentSnapshot documentSnapshot, int position);
}

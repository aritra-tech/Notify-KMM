//
//  NoteItem.swift
//  iosApp
//
//  Created by Aritra on 12/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteItem: View {
    
    var note: Note
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(note.title)
                    .font(.title3)
                    .fontWeight(.semibold)
                
                Spacer()
            }
            
            Text(note.description_)
                .fontWeight(.light)
                .padding(.bottom, 3)
            
            Text(DateTimeUtils().formatAsDate(dateTime: note.createdAt))
                .font(.footnote)
                .fontWeight(.light)
            
        }
        .padding()
        .clipShape(RoundedRectangle(cornerRadius: 5.0))
    }
}

#Preview {
    NoteItem(
        note: Note(id: nil, title: "Notify KMM", description: "Notify KMM is a simple note taking app", createdAt: DateTimeUtils().now())
    )
}

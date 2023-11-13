//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Aritra on 12/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteListScreen: View {
    
    private var noteDataSource: NoteDataSource
    @StateObject var viewModel = NoteListViewModel(noteDataSource: nil)
    
    @State private var isNoteSelected = true
    @State private var selectedNoteId: Int64? = nil
    
    init(noteDataSource: NoteDataSource) {
        self.noteDataSource = noteDataSource
    }
    
    var body: some View {
        
        VStack {
            ZStack {
                
                NavigationLink(destination: NoteDetailsScreen(noteDataSource: self.noteDataSource, noteId: selectedNoteId ), isActive: $isNoteSelected) {
                    EmptyView()
                }.hidden()
                
                HideableSearchTextField<NoteDetailsScreen>(onSearchToggled: {
                    viewModel.toggleIsSearchActive()
                }, destinationProvider: {
                    NoteDetailsScreen(
                        noteDataSource: noteDataSource
                    )
                }, isSearchActive: viewModel.isSearch,
                searchText: $viewModel.searchText
                )
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, minHeight: 50)
                .padding()
                
                if !viewModel.isSearch {
                    Text("All Notes")
                        .font(.title2)
                }
            }
            
            List {
                ForEach(viewModel.filteredNote, id: \.self.id) { note in
                    Button(action: {
                        isNoteSelected = true
                        selectedNoteId = note.id?.int64Value
                    }) {
                        NoteItem(note: note)
                    }
                }
            }
            .onAppear{
                viewModel.loadNotes()
            }
            .listStyle(.plain)
            .listRowSeparator(.hidden)
            
        }
        .onAppear{
            viewModel.setNoteDataSource(noteDataSource: noteDataSource)
        }
    }
}

#Preview {
    EmptyView()
}

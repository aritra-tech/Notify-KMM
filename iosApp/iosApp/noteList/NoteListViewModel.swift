//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by Aritra on 12/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteListScreen {
    @MainActor class NoteListViewModel: ObservableObject {
        
        private var noteDataSource: NoteDataSource? = nil
        private let searchNote = SearchNotes()
        
        private var notes = [Note]()
        @Published private(set) var filteredNote = [Note]()
        
        @Published var searchText = "" {
            didSet {
                self.filteredNote = searchNote.execute(notes: self.notes, query: searchText)
            }
        }
        
        @Published private(set) var isSearch = false
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNotes() {
            noteDataSource?.getAllNotes(completionHandler: { notes, error in
                self.notes = notes ?? []
                self.filteredNote = self.notes
            })
        }
        
        func deleteNoteById(id: Int64?) {
            if id != nil {
                noteDataSource?.deleteNoteById(id: id!, completionHandler: {error in
                    self.loadNotes()
                })
            }
        }
        
        func toggleIsSearchActive() {
            isSearch = !isSearch
            if !isSearch {
                searchText = ""
            }
        }
        
        func setNoteDataSource(noteDataSource: NoteDataSource) {
            self.noteDataSource = noteDataSource
        }
    }
}

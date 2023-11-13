//
//  NoteDetailsViewModel.swift
//  iosApp
//
//  Created by Aritra on 13/11/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension NoteDetailsScreen {
    
    @MainActor class NoteDetailsViewModel: ObservableObject {
        
        private var noteDataSource: NoteDataSource?
        
        private var noteId: Int64? = nil
        @Published var noteTitle = ""
        @Published var noteDescription = ""
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNoteIfExists(id: Int64?) {
            if id != nil {
                self.noteId = id
                noteDataSource?.getNoteById(id: id!, completionHandler: { note, error in
                    self.noteTitle = note?.title ?? ""
                    self.noteDescription = note?.description_ ?? ""
                })
            }
        }
        
        func saveNote(onSaved: @escaping () -> Void) {
            noteDataSource?.insertNote(
                note: Note(id: noteId == nil ? nil : KotlinLong(value: noteId!),
                           title: self.noteTitle, description: self.noteDescription, 
                           createdAt: DateTimeUtils().now()),
                completionHandler: { error in
                    onSaved()
                })
        }
        
        func setParamsAndLoadNote(noteDataSource: NoteDataSource, noteId: Int64?) {
            self.noteDataSource = noteDataSource
            loadNoteIfExists(id: noteId)
        }
    }
}

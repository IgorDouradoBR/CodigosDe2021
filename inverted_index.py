import nltk
import pickle
import os

class InvertedIndex:

    def __init__(self, documents_list=None):
        self.documents_list = documents_list
        self.inverted_index = {}
        if os.path.exists("inv_file.data"):
            self.load_structure()
        else:
            self.inverted_index = self.construct()
            #self.save_structure()

    def save_structure(self):
        dbfile = open("inv_file.data", "ab")
        pickle.dump(self.inverted_index, dbfile)
        dbfile.close()

    def load_structure(self):
        dbfile = open("inv_file.data", "rb")
        db = pickle.load(dbfile)
        self.inverted_index = db
        dbfile.close()

    def get_structure(self):
        return self.inverted_index

    def tokenizer(self, keywords):
        stemmer = nltk.stem.RSLPStemmer()
        return [stemmer.stem(w) for w in keywords.split(" ")]

    def find(self, query):
        query = self.tokenizer(query)
        query = "".join(query)
        try:
            response = self.inverted_index[query]
            return response
        except KeyError as e:
            print(f"Não foi encontrado nenhuma ocorrência da palavra chave \'{query}\'")


    def construct(self):
        data_combined = {}
        for document in self.documents_list:
            for keyword in document.keywords:
                if keyword not in data_combined.keys():
                    data_combined[keyword] = set()
                data_combined[keyword].add(document.filename)

        return data_combined

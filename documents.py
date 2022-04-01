import pdfplumber
import nltk
from nltk.corpus import stopwords
import re
import string

class Documents:

    def __init__(self, filename):
        self.filename = filename
        self.raw_text = list()
        self.keywords = self.extract_keywords()

    def combine_text(self, list_of_text):
        combined_text = "".join(list_of_text)
        return combined_text

    def remove_punctuation(self, text):
        text = re.sub("[%s]" % re.escape(string.punctuation), "", text)
        return text

    def tokenizer(self, text):
        stemmer = nltk.stem.RSLPStemmer()
        return [stemmer.stem(w) for w in text.split(" ")]

    def extract_keywords(self):
        with pdfplumber.open(self.filename) as pdf:
            for pdf_page in pdf.pages:
                single_page = pdf_page.extract_text()
                if single_page:
                    self.raw_text.append(single_page)

            for i, text in enumerate(self.raw_text):
                position = self.raw_text[i].find("Palavras-chave")
                if position > 0:
                    keywords = self.raw_text[i].split("Palavras-chave")[1]
                    break

                position = self.raw_text[i].find("PALAVRAS-CHAVE")

                if position > 0:
                    keywords = self.raw_text[i].split("PALAVRAS-CHAVE")[1]
                    break

        keywords = keywords.replace("\n", "")
        sw = stopwords.words("portuguese")
        keywords = self.combine_text(keywords).lstrip()
        keywords = self.remove_punctuation(keywords)
        keywords = keywords.lstrip()
        keywords = self.tokenizer(keywords)
        keywords_clean = list()

        for k in keywords:
            if k not in sw:
                keywords_clean.append(k)

        return keywords_clean

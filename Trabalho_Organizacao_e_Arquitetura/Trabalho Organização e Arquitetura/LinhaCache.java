public class LinhaCache {

        Integer bitValidade;
        Integer qtdPalavras;
        Integer countAccess;
        Integer memAssociativa;
        public LinhaCache(Integer qtdPlavras,Integer countAccess){
            this.bitValidade = -1;
            this.memAssociativa = -1;
            this.qtdPalavras = qtdPalavras;
            this.countAccess =  countAccess;
        }

        public Integer getbitvalidade(){
            return this.bitValidade;
        }

        public Integer getQtdPalavras(){
            return this.qtdPalavras;
        }

        public Integer getCountAccess(){
            return this.countAccess;
        }

        public void somaCountAccess(){
            this.countAccess++;
        }
        
        public Integer getmemAssociativa(){
            return this.memAssociativa;
        }

        public void setmemAssociativa(int memAssociativa){
            this.memAssociativa = memAssociativa;
        }

        public void setbitvalidade(int bitValidade){
            this.bitValidade = bitValidade;
        }
        
        public void setQtdPalavras(int QtdPalavras){
            this.qtdPalavras = qtdPalavras;
        }
        
        public void setCountAccess(int countAccess){
            this.countAccess = countAccess;
        }

} 
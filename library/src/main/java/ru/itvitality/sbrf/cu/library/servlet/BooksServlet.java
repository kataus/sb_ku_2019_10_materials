package ru.itvitality.sbrf.cu.library.servlet;

import ru.itvitality.sbrf.cu.library.dao.BookDao;
import ru.itvitality.sbrf.cu.library.dao.impl.BookDaoImpl;
import ru.itvitality.sbrf.cu.library.entities.Book;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BooksServlet extends HttpServlet {
    private final BookDao bookDao = new BookDaoImpl();

    private static final String PAGE_START = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>Библиотека</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "\t<h1>Список книг</h1>\n" +
            "\t<p>\n";
    private static final String PAGE_END = "\t</p>\n" +
            "</body>\n" +
            "</html>";

    @Override
    public void doGet( HttpServletRequest req, HttpServletResponse response ) throws IOException {
        StringBuilder sb = new StringBuilder( PAGE_START );

        List<Book> books = bookDao.list();

        Map<String, Book> booksMap = new ConcurrentHashMap<>();
        books.forEach( b -> booksMap.putIfAbsent( b.getIsbn(), b ) );

        booksMap.values().forEach( book -> {
            sb.append( "<a href='/book/" ).append( book.getIsbn() )
                    .append( "'>" ).append( book.getAuthor() )
                    .append( ". " ).append( book.getName() ).append( "</a><br/>" );
        } );


        sb.append( PAGE_END );


        response.setContentType( "text/html" );
        response.setStatus( HttpServletResponse.SC_OK );
        PrintWriter printWriter = response.getWriter();
        printWriter.print( sb.toString() );
        printWriter.flush();
    }

    private class BookHolder {
        private Book book;

        @Override
        public boolean equals( Object o ) {
            if ( this == o ) return true;
            if ( o == null || getClass() != o.getClass() ) return false;
            BookHolder that = (BookHolder) o;
            return book.getIsbn().equals( that.book.getIsbn() );
        }

        @Override
        public int hashCode() {
            return Objects.hash( book );
        }

        public BookHolder( Book book ) {
            this.book = book;
        }
    }
}

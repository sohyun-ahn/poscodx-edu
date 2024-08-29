package ex02.repository.guerydsl;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ex02.domain.Board;
import ex02.domain.dto.BoardDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static ex02.domain.QBoard.board;
// jpa 기본 repository가 맘에 안들기 때문에 이것을 만들게 된다~!
public class QuerydslBoardRepositoryImpl extends QuerydslRepositorySupport implements QuerydslBoardRepository {

    private JPAQueryFactory queryFactory;

    public QuerydslBoardRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Board.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public BoardDto findById02(Integer id) {
        return queryFactory
                .select(Projections.fields(BoardDto.class, board.id, board.hit, board.title, board.content, board.regDate, board.user().name.as("userName")))
                .from(board)
                .innerJoin(board.user())
                .where(board.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<Board> findAllByOrderByRegDateDesc02() {
        return queryFactory
                .select(board)
                .from(board)
                .innerJoin(board.user()).fetchJoin()
                .orderBy(board.regDate.desc())
                .fetch();
    }

    @Override
    public List<BoardDto> findAllByOrderByRegDateDesc03() {
        return queryFactory
                .select(Projections.fields(BoardDto.class, board.id, board.hit, board.title, board.content, board.regDate, board.user().name.as("userName")))
                .from(board)
                .innerJoin(board.user())
                .orderBy(board.regDate.desc())
                .fetch();
    }

    @Override
    public List<BoardDto> findAllByOrderByRegDateDesc03(Integer page, Integer size) {
        return queryFactory
                .select(Projections.fields(BoardDto.class, board.id, board.hit, board.title, board.content, board.regDate, board.user().name.as("userName")))
                .from(board)
                .innerJoin(board.user())
                .orderBy(board.regDate.desc())
                .offset(page * size)
                .limit(size)
                .fetch();
    }

    @Override
    public List<BoardDto> findAll02(Pageable pageable) {
        JPAQuery<BoardDto> query = queryFactory
                .select(Projections.fields(BoardDto.class, board.id, board.hit, board.title, board.content, board.regDate, board.user().name.as("userName")))
                .from(board)
                .innerJoin(board.user());

        if (pageable != null) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());

            for (Sort.Order o : pageable.getSort()) {
                PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
                query.orderBy(new OrderSpecifier(o.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC, orderByExpression.get(o.getProperty())));
            }
        }

        return query.fetch();
    }

    @Override
    public List<BoardDto> findAll02(String keyword, Pageable pageable) {
        JPAQuery<BoardDto> query = queryFactory
                .select(Projections.fields(BoardDto.class, board.id, board.hit, board.title, board.content, board.regDate, board.user().name.as("userName")))
                .from(board)
                .innerJoin(board.user())
                .where(board.title.contains(keyword).or(board.content.contains(keyword)));

        if (pageable != null) {
            query.offset(pageable.getOffset());
            query.limit(pageable.getPageSize());
            for (Sort.Order o : pageable.getSort()) {
                PathBuilder orderByExpression = new PathBuilder(Board.class, "board");
                query.orderBy(new OrderSpecifier(o.isAscending() ? com.querydsl.core.types.Order.ASC : com.querydsl.core.types.Order.DESC, orderByExpression.get(o.getProperty())));
            }
        }

        return query.fetch();
    }

    @Override
    public Long update(Board argBoard) {
        return queryFactory
                .update(board)
                .set(board.title, argBoard.getTitle())
                .set(board.content, argBoard.getContent())
                .where(board.id.eq(argBoard.getId()))
                .execute();
    }

    @Override
    public Long delete(Integer id) {
        return queryFactory
                .delete(board)
                .where(board.id.eq(id))
                .execute();
    }

    @Override
    public Long delete(Integer id, Integer userId) {
        return queryFactory
                .delete(board)
                .where(board.id.eq(id).and(board.user().id.eq(userId)))
                .execute();
    }
}

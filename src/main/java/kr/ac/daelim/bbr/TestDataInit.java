package kr.ac.daelim.bbr;

import kr.ac.daelim.bbr.domain.book.Book;
import kr.ac.daelim.bbr.domain.book.BookRepository;
import kr.ac.daelim.bbr.domain.member.Member;
import kr.ac.daelim.bbr.domain.member.MemberRepository;
import kr.ac.daelim.bbr.domain.member.MemberType;
import kr.ac.daelim.bbr.domain.registration.Registration;
import kr.ac.daelim.bbr.domain.registration.RegistrationRepository;
import kr.ac.daelim.bbr.domain.registration.RegistrationStatus;
import kr.ac.daelim.bbr.domain.uploadfile.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final RegistrationRepository registrationRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        //USER
        Member member = Member.builder()
                .username("test")
                .password("test!")
                .name("테스터")
                .department("컴퓨터정보학부")
                .memberType(MemberType.USER)
                .point(50000)
                .build();
        memberRepository.save(member);

        //ADMIN
        Member admin = Member.builder()
                .username("admin")
                .password("test!")
                .name("관리자")
                .memberType(MemberType.ADMIN)
                .build();
        memberRepository.save(admin);

        /**
         * 컴퓨터정보학부
         */
        Book html = Book.builder()
                .title("Do it! HTML5+CSS3 웹 표준의 정석(2017)(전면개정판)")
                .author("고경희")
                .publisher("이지스퍼블리싱")
                .datetime("2017-01-03")
                .price(28000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1638448%3Ftimestamp%3D20210911161037")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(html);
        Registration regiHtml = Registration.builder()
                .book(html)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiHtml);

        Book js = Book.builder()
                .title("Do it! 자바스크립트 + 제이쿼리 입문(전면개정판)")
                .author("정인용")
                .publisher("이지스퍼블리싱")
                .datetime("2018-04-06")
                .price(20000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1608020%3Ftimestamp%3D20211016150857")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(js);
        Registration regiJs = Registration.builder()
                .book(js)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiJs);

        Book data = Book.builder()
                .title("자료구조(열혈강의)")
                .author("이상진")
                .publisher("프리렉")
                .datetime("2010-01-15")
                .price(28000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1295040%3Ftimestamp%3D20210721144711")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(data);
        Registration regiData = Registration.builder()
                .book(data)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiData);

        Book algo = Book.builder()
                .title("알고리즘")
                .author("산죠이 다스굽타,크리스토스 파파디미트리우,우메쉬 바지라니")
                .publisher("프리렉")
                .datetime("2016-03-22")
                .price(30000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F914455%3Ftimestamp%3D20210925161448")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(algo);
        Registration regiAlgo = Registration.builder()
                .book(algo)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiAlgo);

        Book c = Book.builder()
                .title("이것이 C 언어다, 서현우의 C 프로그래밍 정복")
                .author("서현우")
                .publisher("한빛미디어")
                .datetime("2014-05-20")
                .price(25000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F942948%3Ftimestamp%3D20210103150008")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(c);
        Registration regiC = Registration.builder()
                .book(c)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiC);

        Book cpp = Book.builder()
                .title("어서와 C++는 처음이지!")
                .author("천인국")
                .publisher("인피니티북스")
                .datetime("2018-01-15")
                .price(30000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1621021%3Ftimestamp%3D20211016152831")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(cpp);
        Registration regiCpp = Registration.builder()
                .book(cpp)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiCpp);

        Book java = Book.builder()
                .title("명품 JAVA Programming(개정판 4판)")
                .author("황기태,김효수")
                .publisher("생능출판")
                .datetime("2018-06-01")
                .price(33000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F961489%3Ftimestamp%3D20211016153525")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(java);
        Registration regiJava = Registration.builder()
                .book(java)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiJava);

        Book javaa = Book.builder()
                .title("Java의 정석(3판)")
                .author("남궁성")
                .publisher("도우출판")
                .datetime("2016-01-27")
                .price(30000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1422248%3Ftimestamp%3D20211016152924")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(javaa);
        Registration regiJavaa = Registration.builder()
                .book(javaa)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiJavaa);

        Book spring = Book.builder()
                .title("토비의 스프링 3.1 Vol. 1: 스프링의 이해와 원리(에이콘 오픈소스...")
                .author("이일민")
                .publisher("에이콘출판")
                .datetime("2012-09-21")
                .price(40000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F839315%3Ftimestamp%3D20211016154309")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(spring);
        Registration regiSpring = Registration.builder()
                .book(spring)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiSpring);

        Book jpa = Book.builder()
                .title("자바 ORM 표준 JPA 프로그래밍(에이콘 오픈 소스 프로그래밍 시리즈)")
                .author("김영한")
                .publisher("에이콘출판")
                .datetime("2015-07-28")
                .price(43000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F838090%3Ftimestamp%3D20211016152609")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(jpa);
        Registration regiJpa = Registration.builder()
                .book(jpa)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiJpa);

        Book oop = Book.builder()
                .title("객체지향의 사실과 오해(위키북스 IT Leaders 23)")
                .author("조영호")
                .publisher("위키북스")
                .datetime("2015-06-17")
                .price(20000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1502595%3Ftimestamp%3D20211016153106")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(oop);
        Registration regiOop = Registration.builder()
                .book(oop)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiOop);

        Book object = Book.builder()
                .title("오브젝트(IT Leaders 29)")
                .author("조영호")
                .publisher("위키북스")
                .datetime("2019-06-17")
                .price(38000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F4957834%3Ftimestamp%3D20211016153716")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(object);
        Registration regiObject = Registration.builder()
                .book(object)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiObject);

        Book ddd = Book.builder()
                .title("DDD Start!")
                .author("최범균")
                .publisher("지앤선")
                .datetime("2016-05-27")
                .price(25000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1405948%3Ftimestamp%3D20210917155304")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(ddd);
        Registration regiDdd = Registration.builder()
                .book(ddd)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiDdd);

        Book python = Book.builder()
                .title("Do it! 점프 투 파이썬")
                .author("박응용")
                .publisher("이지스퍼블리싱")
                .datetime("2016-03-03")
                .price(18800)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1481287%3Ftimestamp%3D20210402150811")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(python);
        Registration regiPython = Registration.builder()
                .book(python)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiPython);

        Book oracle = Book.builder()
                .title("Do it! 오라클로 배우는 데이터베이스 입문")
                .author("이지훈")
                .publisher("이지스퍼블리싱")
                .datetime("2018-10-30")
                .price(25000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F3938139%3Ftimestamp%3D20211016153925")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(oracle);
        Registration regiOracle = Registration.builder()
                .book(oracle)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiOracle);

        Book vue = Book.builder()
                .title("Do it! Vue.js 입문")
                .author("장기효")
                .publisher("이지스퍼블리싱")
                .datetime("2018-01-27")
                .price(15000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1651447%3Ftimestamp%3D20211016152823")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(vue);
        Registration regiVue = Registration.builder()
                .book(vue)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiVue);

        Book android = Book.builder()
                .title("안드로이드 앱 프로그래밍(Do it!)(전면개정판 8판)")
                .author("정재곤")
                .publisher("이지스퍼블리싱")
                .datetime("2021-06-28")
                .price(40000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F5751286%3Ftimestamp%3D20211016154553")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(android);
        Registration regiAndroid = Registration.builder()
                .book(android)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiAndroid);

        Book ios = Book.builder()
                .title("iOS 입문 개발자를 위한 아이폰 앱 프로그래밍(Do it)")
                .author("소영섭,허성연")
                .publisher("이지스퍼블리싱")
                .datetime("2014-04-09")
                .price(28000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1489336%3Ftimestamp%3D20210424143734")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(ios);
        Registration regiIos = Registration.builder()
                .book(ios)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiIos);

        Book node = Book.builder()
                .title("Node.js 마이크로서비스 코딩 공작소")
                .author("정대천")
                .publisher("길벗")
                .datetime("2018-02-28")
                .price(26000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1596345%3Ftimestamp%3D20211003195012")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(node);
        Registration regiNode = Registration.builder()
                .book(node)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiNode);

        Book mySql = Book.builder()
                .title("이것이 MySQL이다")
                .author("우재남")
                .publisher("한빛미디어")
                .datetime("2016-06-10")
                .price(30000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F943139%3Ftimestamp%3D20210320143736")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(mySql);
        Registration regiMySql = Registration.builder()
                .book(mySql)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("컴퓨터정보학부")
                .build();
        registrationRepository.save(regiMySql);

        /**
         * 교양과목
         */
        Book justice = Book.builder()
                .title("정의란 무엇인가(마이클 샌델)")
                .author("마이클 샌델")
                .publisher("와이즈베리")
                .datetime("2014-11-20")
                .price(15000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F545848%3Ftimestamp%3D20211016154740")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(justice);
        Registration regiJustice = Registration.builder()
                .book(justice)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(regiJustice);

        Book justice2 = Book.builder()
                .title("정의란 무엇인가")
                .author("마이클 샌델")
                .publisher("김영사")
                .datetime("2010-10-20")
                .price(15000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F519868%3Ftimestamp%3D20211004142535")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(justice2);
        Registration justice2Regi = Registration.builder()
                .book(justice2)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(justice2Regi);

        Book coum = Book.builder()
                .title("의사소통(커플힐링 시리즈)")
                .author("댄 알렌더,트렘퍼 롱맨 3세")
                .publisher("은혜출판사")
                .datetime("2014-03-05")
                .price(3500)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1103951%3Ftimestamp%3D20210430144926")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(coum);
        Registration RegiCoum = Registration.builder()
                .book(coum)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(RegiCoum);

        Book human = Book.builder()
                .title("인간관계론(대인관계기술 향상을 위한)")
                .author("최세영,한주빈,오봉욱")
                .publisher("정민사")
                .datetime("2017-06-25")
                .price(18000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F772785%3Ftimestamp%3D20210105142819")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(human);
        Registration RegiHuman = Registration.builder()
                .book(human)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(RegiHuman);

        Book team = Book.builder()
                .title("가상 환경의 팀워크(큰글씨책)(커뮤니케이션이해총서)")
                .author("박준기,이세윤")
                .publisher("커뮤니케이션북스")
                .datetime("2017-12-29")
                .price(20000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1534454%3Ftimestamp%3D20201211140721")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(team);
        Registration RegiTeam = Registration.builder()
                .book(team)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(RegiTeam);

        Book love = Book.builder()
                .title("그들이 그렇게 연애하는 까닭")
                .author("아미르 레빈,레이첼 헬러")
                .publisher("랜덤하우스")
                .datetime("2011-11-11")
                .price(13000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F414626%3Ftimestamp%3D20211016154302")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(love);
        Registration RegiLove = Registration.builder()
                .book(love)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(RegiLove);

        Book homo = Book.builder()
                .title("호모 데우스")
                .author("유발 하라리")
                .publisher("김영사")
                .datetime("2017-05-19")
                .price(22000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F521168%3Ftimestamp%3D20211016152659")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(homo);
        Registration RegiHomo = Registration.builder()
                .book(homo)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(RegiHomo);

        Book sam = Book.builder()
                .title("삼국지(한 권으로 읽는)(양장본 HardCover)")
                .author("장연")
                .publisher("김영사")
                .datetime("2010-06-30")
                .price(14000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F519574%3Ftimestamp%3D20211004142557")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(sam);
        Registration RegiSam = Registration.builder()
                .book(sam)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(RegiSam);

        Book bingo = Book.builder()
                .title("취업의 정답")
                .author("하정필")
                .publisher("지형")
                .datetime("2010-08-25")
                .price(13000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F1388023%3Ftimestamp%3D20210803150527")
                .stockQuantity(1)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(bingo);
        Registration RegiBingo = Registration.builder()
                .book(bingo)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(RegiBingo);

        Book self = Book.builder()
                .title("자기소개서 혁명(취업의 신 박장호 대표의)")
                .author("박장호")
                .publisher("성안당")
                .datetime("2017-09-15")
                .price(19000)
                .thumbnail("https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F490206%3Ftimestamp%3D20210201151034")
                .stockQuantity(6)
                .views(0)
                .registDt(LocalDateTime.now())
                .build();
        bookRepository.save(self);
        Registration RegiSelf = Registration.builder()
                .book(self)
                .member(member)
                .status(RegistrationStatus.COMP)
                .department("교양과목")
                .build();
        registrationRepository.save(RegiSelf);
        Registration RegiSelf2 = Registration.builder()
                .book(self)
                .member(member)
                .status(RegistrationStatus.COMP)
                .clazz("진로설계와 자기계발")
                .state("깨끗함")
                .etc("이상없음")
                .build();
        registrationRepository.save(RegiSelf2);
        Registration RegiSelf3 = Registration.builder()
                .book(self)
                .member(member)
                .status(RegistrationStatus.COMP)
                .clazz("직업윤리")
                .state("깨끗해요!")
                .build();
        registrationRepository.save(RegiSelf3);
        Registration RegiSelf4 = Registration.builder()
                .book(self)
                .member(member)
                .status(RegistrationStatus.COMP)
                .clazz("직장생활과 예절")
                .state("깨끗깨끗")
                .build();
        registrationRepository.save(RegiSelf4);
        Registration RegiSelf5 = Registration.builder()
                .book(self)
                .member(member)
                .status(RegistrationStatus.COMP)
                .clazz("윤리와 사상")
                .state("더럽")
                .build();
        registrationRepository.save(RegiSelf5);
        Registration RegiSelf6 = Registration.builder()
                .book(self)
                .member(member)
                .status(RegistrationStatus.COMP)
                .clazz("직장생활과 취업전략")
                .state("보통")
                .etc("혁명적")
                .build();
        registrationRepository.save(RegiSelf6);
    }
}
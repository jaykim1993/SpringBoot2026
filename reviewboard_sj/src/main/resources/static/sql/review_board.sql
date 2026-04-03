CREATE TABLE review_board (
    id INT AUTO_INCREMENT PRIMARY KEY,   -- 리뷰 번호
    title VARCHAR(100) NOT NULL,          -- 제목
    content TEXT NOT NULL,                -- 내용
    writer VARCHAR(50) NOT NULL,           -- 작성자
    rate TINYINT NOT NULL, -- 별점 (1~5)
    createdAt DATETIME NOT NULL DEFAULT NOW(), -- 작성일
    readCount INT NOT NULL DEFAULT 0      -- 조회수
);

INSERT INTO review_board (title, content, writer, rate)
VALUES 
('테스트 제목 1', '테스트 내용 1', 'Writer1', FLOOR(1 + RAND()*5)),
('테스트 제목 2', '테스트 내용 2', 'Writer2', FLOOR(1 + RAND()*5)),
('테스트 제목 3', '테스트 내용 3', 'Writer3', FLOOR(1 + RAND()*5)),
('테스트 제목 4', '테스트 내용 4', 'Writer4', FLOOR(1 + RAND()*5)),
('테스트 제목 5', '테스트 내용 5', 'Writer5', FLOOR(1 + RAND()*5)),
('테스트 제목 6', '테스트 내용 6', 'Writer1', FLOOR(1 + RAND()*5)),
('테스트 제목 7', '테스트 내용 7', 'Writer2', FLOOR(1 + RAND()*5)),
('테스트 제목 8', '테스트 내용 8', 'Writer3', FLOOR(1 + RAND()*5)),
('테스트 제목 9', '테스트 내용 9', 'Writer4', FLOOR(1 + RAND()*5)),
('테스트 제목 10', '테스트 내용 10', 'Writer5', FLOOR(1 + RAND()*5)),
('테스트 제목 11', '테스트 내용 11', 'Writer1', FLOOR(1 + RAND()*5)),
('테스트 제목 12', '테스트 내용 12', 'Writer2', FLOOR(1 + RAND()*5)),
('테스트 제목 13', '테스트 내용 13', 'Writer3', FLOOR(1 + RAND()*5)),
('테스트 제목 14', '테스트 내용 14', 'Writer4', FLOOR(1 + RAND()*5)),
('테스트 제목 15', '테스트 내용 15', 'Writer5', FLOOR(1 + RAND()*5)),
('테스트 제목 16', '테스트 내용 16', 'Writer1', FLOOR(1 + RAND()*5)),
('테스트 제목 17', '테스트 내용 17', 'Writer2', FLOOR(1 + RAND()*5)),
('테스트 제목 18', '테스트 내용 18', 'Writer3', FLOOR(1 + RAND()*5)),
('테스트 제목 19', '테스트 내용 19', 'Writer4', FLOOR(1 + RAND()*5)),
('테스트 제목 20', '테스트 내용 20', 'Writer5', FLOOR(1 + RAND()*5));



SELECT * FROM springbootdb.review_board;
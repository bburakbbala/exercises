using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface ITestResultTestRepository : IRepositoryAsync<TestResultTest>
    {
        void Update(TestResultTest testResultTest);
    }
}